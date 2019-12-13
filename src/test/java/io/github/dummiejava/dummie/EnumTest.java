package io.github.dummiejava.dummie;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;

import io.github.dummiejava.dummie.configuration.GenerationStrategy;
import org.junit.Test;

public class EnumTest {

    @Test
    public void should_create_object_with_enum_field() {
        EnumData enumData = Dummie.create(EnumData.class);

        assertThat(enumData, not(nullValue()));
        assertThat(enumData.getDataType(), equalTo(DataType.STRING));
    }

    @Test
    public void should_create_object_without_any_constant() {
        EmptyEnumData emptyEnumData = Dummie.create(EmptyEnumData.class);

        assertThat(emptyEnumData, not(nullValue()));
        assertThat(emptyEnumData.getDataType(), is(nullValue()));
    }

    @Test
    public void should_create_object_with_random_strategy() throws Exception {
        EnumData enumData = Dummie.withStrategy(GenerationStrategy.RANDOM)
            .create(EnumData.class);

        assertThat(enumData, not(nullValue()));
        assertThat(enumData.getDataType(), not(nullValue()));
    }

    @Test
    public void should_create_object_with_random_strategy_and_empty_constant() throws Exception {
        EmptyEnumData emptyEnumData = Dummie.withStrategy(GenerationStrategy.RANDOM)
            .create(EmptyEnumData.class);

        assertThat(emptyEnumData, not(nullValue()));
        assertThat(emptyEnumData.getDataType(), is(nullValue()));
    }

    @Test
    public void should_cache_same_type_field_with_diff_field() {
        CacheEnumData cacheEnumData = Dummie.prepare(CacheEnumData.class)
                .override("dataType", DataType.INTEGER)
                .override("dataType1", DataType.FLOAT)
                .build();

        assertThat(cacheEnumData, not(nullValue()));
        assertEquals(DataType.INTEGER, cacheEnumData.getDataType());
        assertEquals(DataType.FLOAT, cacheEnumData.getDataType1());
        assertEquals(DataType.STRING, cacheEnumData.getDataType2());
    }

    public enum DataType {
        STRING, INTEGER, FLOAT
    }
    public static class EnumData {
        private DataType dataType;

        public DataType getDataType() {
            return dataType;
        }

        public void setDataType(DataType dataType) {
            this.dataType = dataType;
        }
    }

    public enum EmptyDataType {
    }

    public static class EmptyEnumData {
        private EmptyDataType dataType;

        public EmptyDataType getDataType() {
            return dataType;
        }

        public void setDataType(EmptyDataType dataType) {
            this.dataType = dataType;
        }
    }

    public static class CacheEnumData {
        private DataType dataType;

        private DataType dataType1;

        private DataType dataType2;

        public DataType getDataType() {
            return dataType;
        }

        public void setDataType(DataType dataType) {
            this.dataType = dataType;
        }

        public DataType getDataType1() {
            return dataType1;
        }

        public void setDataType1(DataType dataType1) {
            this.dataType1 = dataType1;
        }

        public DataType getDataType2() {
            return dataType2;
        }

        public void setDataType2(DataType dataType2) {
            this.dataType2 = dataType2;
        }
    }
}
