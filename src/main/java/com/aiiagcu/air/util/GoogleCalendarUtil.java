package com.aiiagcu.air.util;

import com.aiiagcu.air.dto.CalendarOutput;
import com.aiiagcu.air.service.CalendarService;
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
import com.google.api.services.calendar.model.EventDateTime;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GoogleCalendarUtil {
    private static final String GOOGLE_CALENDAR_ID = "gytjd0105@gmail.com";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.all().toString());
    private static final String CLIENT_SECRET_DIR = "/credentials.json";
    
    public static LocalDateTime convertEventDateTimeToLocalDateTime(EventDateTime eventDateTime) {
        if (eventDateTime.getDateTime() != null) {
            return LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(eventDateTime.getDateTime().getValue()),
                    ZoneId.systemDefault()
            );
        }
        return LocalDateTime.ofInstant(
                Instant.ofEpochMilli(eventDateTime.getDate().getValue()),
                ZoneId.systemDefault()
        ).withHour(0).withMinute(0).withSecond(0).withNano(0);
    }
//
//    private Calendar createGoogleCalendarService() throws GeneralSecurityException, IOException {
//        NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
//        return new Calendar.Builder(httpTransport, JSON_FACTORY, getCredentials(httpTransport))
//                .build();
//    }
//
//    private Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
//        // Load client secrets.
//        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(CalendarService.class.getResourceAsStream(CLIENT_SECRET_DIR)));
//        // Build flow and trigger user authorization request.
//        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
//                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File("credentials")))
//                .setAccessType("offline")
//                .build();
//        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
//    }
//
//    public List<CalendarOutput> findCalendar(Integer date) throws IOException, GeneralSecurityException {
//        Calendar service = createGoogleCalendarService();
//        List<CalendarOutput> calendarOutputs = service.events().list(GOOGLE_CALENDAR_ID).setTimeZone("Asia/Seoul")
//                .setTimeMin(DateTime.parseRfc3339(LocalDateTime.of(date / 100, date % 100, 1, 0, 0, 0).minusDays(6).withNano(1).toString()))
//                .setTimeMax(DateTime.parseRfc3339(LocalDateTime.of(date / 100, date % 100, Month.of(date % 100).minLength(), 23, 59, 59).plusDays(6).toString()))
//                .setFields("items(id,creator/email,start,end,summary,location)")
//                .execute().getItems().stream().map(CalendarOutput::new).collect(Collectors.toList());
//
//        calendarOutputs.addAll(service.events().list("ko.south_korea#holiday@group.v.calendar.google.com").setTimeZone("Asia/Seoul")
//                .setTimeMin(DateTime.parseRfc3339(LocalDateTime.of(date / 100, date % 100, 1, 0, 0, 0).minusDays(6).withNano(1).toString()))
//                .setTimeMax(DateTime.parseRfc3339(LocalDateTime.of(date / 100, date % 100, Month.of(date % 100).minLength(), 23, 59, 59).plusDays(6).toString()))
//                .setFields("items(id,creator,start,end,summary,location)")
//                .execute().getItems().stream().map(CalendarOutput::new).toList());
//        return calendarOutputs;
//    }
}
