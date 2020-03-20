package ru.vallball.biblio01.model;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MyCustomDeserializer extends JsonDeserializer<Book> {

	private ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public Book deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		ObjectCodec oc = p.getCodec();
        JsonNode node = oc.readTree(p);
        Book book = mapper.treeToValue(node, Book.class);
       	return book;
	}
}
