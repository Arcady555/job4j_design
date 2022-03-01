package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Arcady Parfenov"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test
    public void whenOnlyCommentEmptyStrings() {
        String path = "./data/test2.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value(String.class.toString()), is(Matchers.nullValue()));
    }

    /**
    @Test (expected = IllegalArgumentException.class)
    public void whenWrongForm() {
        String path = "./data/test3.properties";
        Config config = new Config(path);
        config.load();
    }
    */
}