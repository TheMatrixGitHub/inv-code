package reflect;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.lang.reflect.Method;

public class ReflectionTest {
    private static int count = 0;

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ReflectionTest.class.getSimpleName())
                .forks(1)
                .warmupIterations(2)
                .measurementIterations(2)
//                .output("E:/Benchmark.log")
                .build();

        new Runner(opt).run();
    }


    public static void foo() {
//        new Exception("test#" + (count++)).printStackTrace();
    }


    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void invokeWithReflect() throws Exception {
        for (int i = 0; i < 15; i++) {
            Class<?> clz = Class.forName("reflect.ReflectionTest");
            Method method = clz.getMethod("foo");
            method.invoke(null);
        }

    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void invoke() {
        for (int i = 0; i < 15; i++) {
            foo();
        }

    }
}