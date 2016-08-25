import com.zzz.robot.util.Request;

/**
 * Created by dell_2 on 2016/8/25.
 */
public class Test {

    public static void main(String[] args) {
        Request request = new Request();
        String content = request.get("http://www.biquge66.com/10_10292");
        System.out.println(content);
    }
}
