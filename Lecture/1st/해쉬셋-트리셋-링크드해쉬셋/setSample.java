import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Set;

public class SetSample {

    public static void main(String[] args) {
        String[] data = {"sds", "sk", "google", "cns", "naver", "blizard", "apple", "samsung"};
        Set<String> hs = new HashSet<String>();
        for (String s : data) hs.add(s);
        System.out.println(hs);

        //정렬
        Set<String> ts = new TreeSet<String>();
        for (String s : data) ts.add(s);
        System.out.println(ts);

        //입력 순서
        Set<String> ls = new LinkedHashSet<String>();
        for (String s : data) ls.add(s);
        System.out.println(ls);
    }
}