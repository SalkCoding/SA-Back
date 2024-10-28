package com.aiiagcu.air.service;

import com.aiiagcu.air.dto.CalendarOutput;
import com.aiiagcu.air.dto.CalenderInput;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final DiscordWebHookService discordWebHookService;
//    private static final String GOOGLE_CALENDAR_ID = "e79a52f4ce0701e429bbdd7f8532b231fd8429fd105f0960b17dcb0136bd584e@group.calendar.google.com";
    private static final String GOOGLE_CALENDAR_ID = "689c43fb51c0e40be9f900964fca1ca52bd10c6e8c0b9db3ba08c25e3945229c@group.calendar.google.com";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);
    private static final String CLIENT_SECRET_DIR = "/credentials.json";
    
    private Calendar createGoogleCalendarService() throws GeneralSecurityException, IOException {
        NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        return new Calendar.Builder(httpTransport, JSON_FACTORY, getCredentials(httpTransport))
                .build();
    }
    
    private Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(CalendarService.class.getResourceAsStream(CLIENT_SECRET_DIR)));
        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File("credentials")))
                .setAccessType("offline")
                .build();
        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }
    
    public List<CalendarOutput> findCalendar(Integer date) throws IOException, GeneralSecurityException {
        Calendar service = createGoogleCalendarService();
        List<CalendarOutput> calendarOutputs = service.events().list(GOOGLE_CALENDAR_ID).setTimeZone("Asia/Seoul")
                .setTimeMin(DateTime.parseRfc3339(LocalDateTime.of(date / 100, date % 100, 1, 0, 0, 0).minusDays(6).withNano(1).toString()))
                .setTimeMax(DateTime.parseRfc3339(LocalDateTime.of(date / 100, date % 100, Month.of(date % 100).minLength(), 23, 59, 59).plusDays(6).toString()))
                .setFields("items(id,creator/email,start,end,summary,location)")
                .execute().getItems().stream().map(CalendarOutput::new).collect(Collectors.toList());
        return calendarOutputs;
    }
    
    public CalendarOutput addCalendar(CalenderInput calenderInput) throws GeneralSecurityException, IOException {
        Event event = new Event();
        event.setSummary(calenderInput.getTitle())
                .setLocation(calenderInput.getLocation())
                .setStart(new EventDateTime().setDateTime(DateTime.parseRfc3339(calenderInput.getStart().replace(' ', 'T') + ":00+09:00")))
                .setEnd(new EventDateTime().setDateTime(DateTime.parseRfc3339(calenderInput.getEnd().replace(' ', 'T') + ":00+09:00")));
        event = createGoogleCalendarService().events().insert(GOOGLE_CALENDAR_ID, event).execute();
        discordWebHookService.sendWebHook(calenderInput);
        return new CalendarOutput(event);
    }
    
    public CalendarOutput updateCalendar(CalenderInput calenderInput) throws GeneralSecurityException, IOException {
        Event event = new Event();
        event.setSummary(calenderInput.getTitle())
                .setLocation(calenderInput.getLocation())
                .setStart(new EventDateTime().setDateTime(DateTime.parseRfc3339(calenderInput.getStart().replace(' ', 'T') + ":00+09:00")))
                .setEnd(new EventDateTime().setDateTime(DateTime.parseRfc3339(calenderInput.getEnd().replace(' ', 'T') + ":00+09:00")));
        event = createGoogleCalendarService().events().update(GOOGLE_CALENDAR_ID, calenderInput.getId(), event).execute();
        discordWebHookService.sendWebHook(calenderInput);
        return new CalendarOutput(event);

    }
    
    public void deleteCalendar(String id) throws GeneralSecurityException, IOException {
        createGoogleCalendarService().events().delete(GOOGLE_CALENDAR_ID, id).execute();
    }
}
