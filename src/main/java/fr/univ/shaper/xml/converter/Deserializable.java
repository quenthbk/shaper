package fr.univ.shaper.xml.converter;

public interface Deserializable {
    <T> T deserialize(String t, Class<T> clazz);
}
