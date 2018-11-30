package study.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 正则表达式特殊字符。(使用一下特殊字符均需要转义 "\")
 * $ : 匹配输入字符串的结尾位置
 * () : 标记一个子表达式的开始和结束位置。
 * * : 匹配前面的子表达式零次或多次
 * + : 匹配前面的子表达式一次或多次
 * . : 匹配除换行符 \n 之外的任何单字符。
 * [] : 标记一个中括号表达式的开始。
 * ? : 匹配前面的子表达式零次或一次，或指明一个非贪婪限定符。
 * \ : 转义
 * ^ : 匹配输入字符串的开始位置，除非在方括号表达式中使用，此时它表示不接受该字符集合。
 * {} : 标记限定符表达式的开始。
 * | : 指明两项之间的一个选择。
 * -----------------------------------------------------------------------------
 * 正则表达式限定符
 * * : 匹配前面的子表达式零次或多次。例如，zo* 能匹配 "z" 以及 "zoo"。* 等价于{0,}。
 *
 *
 *
 *
 *
 *
 *
 *
 */
public class RegexActivity extends AppCompatActivity {

    public static final String TAG = "Regex_Activity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<String> inputList = new ArrayList<>();
        String input4 = "2abc";
        inputList.add(input4);

        String regex = "(^[0-1]|[0-2])*abc$";
        Pattern compile = Pattern.compile(regex);

        for (int i = 0; i < inputList.size(); i++) {
            Matcher matcher = compile.matcher(inputList.get(i));
            boolean isMatches = matcher.matches();
            Log.d(TAG, "匹配结果：" + isMatches + " ，匹配的内容：" + inputList.get(i) + "\n");
        }


    }
}
