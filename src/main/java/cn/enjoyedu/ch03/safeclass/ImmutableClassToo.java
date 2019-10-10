package cn.enjoyedu.ch03.safeclass;

import java.util.Arrays;
import java.util.List;

/**
 * 类不可变--事实不可变
 */
public class ImmutableClassToo {
    private final List<Integer> list = Arrays.asList(1,2,3);

    public boolean isContain(int i){
        return list.contains(i);
    }
}
