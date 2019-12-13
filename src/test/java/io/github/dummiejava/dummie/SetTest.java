package io.github.dummiejava.dummie;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import io.github.dummiejava.dummie.configuration.GenerationStrategy;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class SetTest {

    @Test
    public void should_create_object_with_set_field() {
        SetData data = Dummie.create(SetData.class);

        assertThat(data, not(nullValue()));
        assertThat(data.getNotParameterized().size(), is(0));
        assertThat(data.getParameterized().size(), is(1));
    }

    @Test
    public void should_create_object_with_hashset_field() {
        HashSetData data = Dummie.create(HashSetData.class);

        assertThat(data, not(nullValue()));
        assertThat(data.getNotParameterized().size(), is(0));
        assertThat(data.getParameterized().size(), is(1));
    }

    @Test
    public void should_create_diff_cache_for_set_and_hashset() throws Exception {
        MixSetData data = Dummie.create(MixSetData.class);

        validateMixSetData(data);
    }

    @Test
    public void should_create_object_with_set_field_and_random_strategy() {
        MixSetData data = Dummie.withStrategy(GenerationStrategy.RANDOM)
            .create(MixSetData.class);

        validateMixSetData(data);
    }

    private void validateMixSetData(MixSetData data) {
        assertThat(data, not(nullValue()));
        assertThat(data.getNotParameterized().size(), is(0));
        assertThat(data.getParameterized().size(), is(1));
        assertThat(data.getParameterized(), not(equalTo((Set)data.getNotParameterizedHashSet())));
    }

    public static class SetData {
        private Set notParameterized;

        private Set<String> parameterized;

        public Set getNotParameterized() {
            return notParameterized;
        }

        public void setNotParameterized(Set notParameterized) {
            this.notParameterized = notParameterized;
        }

        public Set<String> getParameterized() {
            return parameterized;
        }

        public void setParameterized(Set<String> parameterized) {
            this.parameterized = parameterized;
        }
    }

    public static class MixSetData {
        private HashSet notParameterizedHashSet;

        private HashSet<String> parameterizedHashSet;

        private Set notParameterized;

        private Set<String> parameterized;

        public Set getNotParameterized() {
            return notParameterized;
        }

        public void setNotParameterized(Set notParameterized) {
            this.notParameterized = notParameterized;
        }

        public Set<String> getParameterized() {
            return parameterized;
        }

        public void setParameterized(Set<String> parameterized) {
            this.parameterized = parameterized;
        }

        public HashSet getNotParameterizedHashSet() {
            return notParameterizedHashSet;
        }

        public void setNotParameterizedHashSet(HashSet notParameterizedHashSet) {
            this.notParameterizedHashSet = notParameterizedHashSet;
        }

        public HashSet<String> getParameterizedHashSet() {
            return parameterizedHashSet;
        }

        public void setParameterizedHashSet(HashSet<String> parameterizedHashSet) {
            this.parameterizedHashSet = parameterizedHashSet;
        }
    }

    public static class HashSetData {
        private HashSet notParameterized;

        private HashSet<String> parameterized;

        public HashSet getNotParameterized() {
            return notParameterized;
        }

        public void setNotParameterized(HashSet notParameterized) {
            this.notParameterized = notParameterized;
        }

        public HashSet<String> getParameterized() {
            return parameterized;
        }

        public void setParameterized(HashSet<String> parameterized) {
            this.parameterized = parameterized;
        }
    }
}
