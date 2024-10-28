package com.aiiagcu.air.service;

import com.aiiagcu.air.dto.CalenderInput;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DiscordWebHookService {
    @Value("${discord.webhookURL}")
    private  String url;

    @Async
    public void sendWebHook(CalenderInput calenderInput){

        JsonObject webhookInput = new JsonObject();
        webhookInput.addProperty("content", "새로운 동아리 일정이 등록되었습니다");
        webhookInput.addProperty("tts", false);
        JsonArray embeds = new JsonArray();
        JsonObject embed = new JsonObject();
        embed.addProperty("title", calenderInput.getTitle());
        if (calenderInput.getStart().substring(0, 10).equals(calenderInput.getEnd().substring(0, 10))) {
            embed.addProperty("description", "날짜:" + calenderInput.getStart() + " ~ " + calenderInput.getEnd().substring(11) + "\n장소: " + calenderInput.getLocation());

        } else {
            embed.addProperty("description", "날짜:" + calenderInput.getStart() + " ~ " + calenderInput.getEnd() + "\n장소: " + calenderInput.getLocation());

        }
        embeds.add(embed);
        webhookInput.add("embeds", embeds);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        new RestTemplate().postForObject(url, new HttpEntity<>(webhookInput.toString(), headers), Void.class);
    }

}
