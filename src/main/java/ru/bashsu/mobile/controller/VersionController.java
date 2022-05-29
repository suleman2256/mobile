package ru.bashsu.mobile.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.TreeMap;

@RestController
@AllArgsConstructor
@Api(description = "Сервисы для получения информации о версии")
public class VersionController {

    private final BuildProperties buildProperties;

    @ApiOperation("Получить информацию о версии сервиса")
    @GetMapping("/api/version")
    public Map<String, Object> getVersionInfo() {
        Map<String, Object> versionInfo = new TreeMap<>();
        versionInfo.put("name", buildProperties.getName());
        versionInfo.put("group", buildProperties.getGroup().replace("\"", ""));
        versionInfo.put("version", buildProperties.getVersion());
        versionInfo.put("buildDate", buildProperties.getTime());
        return versionInfo;
    }
}
