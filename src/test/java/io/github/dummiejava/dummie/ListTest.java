package io.github.dummiejava.dummie;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import io.github.dummiejava.dummie.configuration.GenerationStrategy;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListTest {
    @Test
    public void should_create_object_with_list_field() throws Exception {
        ListData data = Dummie.create(ListData.class);

        validateSimpleList(data);
    }

    private void validateSimpleList(ListData data) {
        assertThat(data, not(nullValue()));
        assertThat(data.getNotParameterized().size(), is(0));
        assertThat(data.getParameterized().size(), is(1));
        assertThat(data.getParameterized().get(0), not(nullValue()));
    }

    @Test
    public void should_create_object_with_random_strategy() throws Exception {
        ListData data = Dummie.withStrategy(GenerationStrategy.RANDOM).create(ListData.class);

        validateSimpleList(data);
    }

    @Test
    public void should_create_object_with_mix_list_field() throws Exception {
        MixListData data = Dummie.create(MixListData.class);

        validateMixList(data);
    }

    @Test
    public void should_create_object_with_mix_list_field_and_random_strategy() throws Exception {
        MixListData data = Dummie.withStrategy(GenerationStrategy.RANDOM).create(MixListData.class);

        validateMixList(data);
    }

    private void validateMixList(MixListData data) {
        assertThat(data, not(nullValue()));
        assertThat(data.getNotParameterized().size(), is(0));
        assertThat(data.getParameterized().size(), is(1));
        assertThat(data.getParameterized().get(0), not(nullValue()));
    }

    public static class ListData {
        private List notParameterized;

        private List<String> parameterized;

        public List<String> getParameterized() {
            return parameterized;
        }

        public void setParameterized(List<String> parameterized) {
            this.parameterized = parameterized;
        }

        public List getNotParameterized() {
            return notParameterized;
        }

        public void setNotParameterized(List notParameterized) {
            this.notParameterized = notParameterized;
        }
    }

    public static class MixListData {
        private ArrayList notParameterized;

        private LinkedList<String> parameterized;

        public ArrayList getNotParameterized() {
            return notParameterized;
        }

        public void setNotParameterized(ArrayList notParameterized) {
            this.notParameterized = notParameterized;
        }

        public LinkedList<String> getParameterized() {
            return parameterized;
        }

        public void setParameterized(LinkedList<String> parameterized) {
            this.parameterized = parameterized;
        }
    }
}
