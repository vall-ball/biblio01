package ru.vallball.biblio01.model;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

public class MyCustomSerializer extends JsonSerializer<Book>{

	private ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public void serialize(Book value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		StringWriter writer = new StringWriter();
        mapper.writeValue(writer, value);
        gen.writeFieldName(writer.toString());
		
	}

}
