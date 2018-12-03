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
 * + : 匹配前面的子表达式一次或多次。例如，'zo+' 能匹配 "zo" 以及 "zoo"，但不能匹配 "z"。+ 等价于 {1,}。
 * ? : 匹配前面的子表达式零次或一次。例如，"do(es)?" 可以匹配 "do" 、 "does" 中的 "does" 、 "doxy" 中的 "do" 。? 等价于 {0,1}。
 * {n} : n 是一个非负整数。匹配确定的 n 次。例如，'o{2}' 不能匹配 "Bob" 中的 'o'，但是能匹配 "food" 中的两个 o。
 * {n,} : n 是一个非负整数。至少匹配n 次。例如，'o{2,}' 不能匹配 "Bob" 中的 'o'，但能匹配 "foooood" 中的所有 o。'o{1,}' 等价于 'o+'。'o{0,}' 则等价于 'o*'。
 * {n,m} : m 和 n 均为非负整数，其中n <= m。最少匹配 n 次且最多匹配 m 次。例如，"o{1,3}" 将匹配 "fooooood" 中的前三个 o。'o{0,1}' 等价于 'o?'。请注意在逗号和两个数之间不能有空格。
 *
 */
public class RegexActivity extends AppCompatActivity {

    public static final String TAG = "Regex_Activity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<String> inputList = new ArrayList<>();

        //1.匹配整数或者小数（包括正数和负数）
        String input1 = "-123";
        String input2 = "123.123";
        String input3 = "-123.3";
        String input4 = "2.3";
        String input5 = "0.12";
        String input6 = "-0.12";

        String regex1 = "-?[0-9]+(\\.[0-9]+)?";

        inputList.add(input1);
        inputList.add(input2);
        inputList.add(input3);
        inputList.add(input4);
        inputList.add(input5);
        inputList.add(input6);

        //2.匹配年月日日期 格式2018-12-6
        String input7 = "2018-12-06";
        String input8 = "018-12-6";
        String input9 = "20184-12-6";
        String input10 = "2018-123-666";
        String input11 = "2018-12-666";
        String input12 = "2018-12";
        String input13 = "2018";

        String regex2 = "^(\\d{4})-(0[1-9]|1[0-2])-([0-2]\\d|3[0-1])";

        inputList.add(input7);
        inputList.add(input8);
        inputList.add(input9);
        inputList.add(input10);
        inputList.add(input11);
        inputList.add(input12);
        inputList.add(input13);

        //3.匹配qq号 (5-11位)

        String input14 = "1234";
        String input15 = "12345";
        String input16 = "123456";
        String input17 = "123456789";
        String input18 = "12345678901";
        String input19 = "123456789123";
        String input20 = "01234567";

        String regex3 = "^[^0]\\d{5,11}";

        inputList.add(input14);
        inputList.add(input15);
        inputList.add(input16);
        inputList.add(input17);
        inputList.add(input18);
        inputList.add(input19);
        inputList.add(input20);

        Pattern compile = Pattern.compile(regex3);
        for (int i = 0; i < inputList.size(); i++) {
            Matcher matcher = compile.matcher(inputList.get(i));
            boolean isMatches = matcher.matches();
            if (isMatches){
                Log.d(TAG, "匹配结果：" + isMatches + " ，匹配的内容：" + inputList.get(i) + "\n");
            }
        }


    }
}
