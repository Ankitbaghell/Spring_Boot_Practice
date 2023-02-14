package com.jsonFileToJavaObject.jsonFileToJavaObject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@SpringBootApplication

public class JsonFileToJavaObjectApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(JsonFileToJavaObjectApplication.class, args);

		File file = new File("C:/Users/ankit.baghel/Downloads/schwab_2021-08-16_08258106_1234_24_1234 (1).gff");
		ObjectMapper mapper = new ObjectMapper();
		Data data = mapper.readValue(file, Data.class);
		System.out.println(data);

		Map<String, Object> dynamicProperties = data.getAccounts().get(0).getPositions().get(3).getPositionTimeseriesId().getDynamicProperties();
		String position_name = (String)dynamicProperties.get("position_name");
		System.out.println("----------------------------name of position--------------- :  "+position_name);

	}
}



