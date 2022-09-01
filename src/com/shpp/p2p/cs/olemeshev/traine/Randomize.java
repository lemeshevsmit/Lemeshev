package com.shpp.p2p.cs.olemeshev.traine;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Randomize {

    public <T> T randomize(List<T> list) {
        var index = ThreadLocalRandom.current().nextInt(list.size());
        System.out.println(list.get(index));
        return list.get(index);
    }
}
