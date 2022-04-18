import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: sl
 * Date: 2021/10/22
 * Time: 17:46
 */
public class CheckConfig {

    public static void main(String[] args) throws Exception {


        Map<String, String> privateMap = IOUtils.readLines(new InputStreamReader(new FileInputStream("/Users/sl/Downloads/config-private.properties")))
                .stream()
                .filter(s -> s.contains("="))
                .collect(Collectors.toMap(s -> {
                    int index = s.indexOf("=");
                    return s.substring(0, index);
                }, s -> {
                    int index = s.indexOf("=");
                    return s.substring(index + 1);
                }));

        Map<String, String> publicMap = IOUtils.readLines(new InputStreamReader(new FileInputStream("/Users/sl/Downloads/config-relation.properties")))
                .stream()
                .filter(s -> s.contains("="))
                .collect(Collectors.toMap(s -> {
                    int index = s.indexOf("=");
                    return s.substring(0, index);
                }, s -> {
                    int index = s.indexOf("=");
                    return s.substring(index + 1);
                }));

        privateMap.keySet().stream().forEach(key -> {
            if (!privateMap.get(key).equals(publicMap.get(key))) {
                System.out.println(String.format("私有文件的特殊配置 key:%s   value:%s", key, privateMap.get(key)));
            }
        });

    }
}