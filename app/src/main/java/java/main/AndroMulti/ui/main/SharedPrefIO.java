package java.main.AndroMulti.ui.main;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import java.util.regex.Pattern;

public class SharedPrefIO {

    private static final String REGEX =
            "^[A-Fa-f0-9]{14}|[0-9a-fA-F]{2}\\b:[0-9a-fA-F]{2}\\b:[0-9a-fA-F]{2}\\b:[0-9a-fA-F]{2}\\b:[0-9a-fA-F]{2}\\b:[0-9a-fA-F]{2}\\b:[0-9a-fA-F]{2}$";

    public static final String STORED_DATA = "NFC_DATA";
    public static final String STORED_CARD_ID = "CARD_ID";
    public static final String CURRENT_CSV = "CURRENT_CSV";

    private static boolean inlineSetter(@NonNull Context context, String input, String dir, String val) {
        if (RegexCheck(input)) {
            context.getSharedPreferences(dir, Context.MODE_PRIVATE)
                    .edit().putString(val, input).apply();
            return true;
        }
        return false;
    }

    private static String inlineGetter(@NonNull Context context, String dir, String val) {
        return context.getSharedPreferences(dir, Context.MODE_PRIVATE)
                .getString(val, null);
    }

    public static boolean setStoredCardID(@NonNull Context context, String input) {
        return inlineSetter(context, input, STORED_DATA, STORED_CARD_ID);
    }

    public static String getStoredCardID(@NonNull Context context) {
        return inlineGetter(context, STORED_DATA, STORED_CARD_ID);
    }

    public static boolean setCurrentCSV(@NonNull Context context, String input) {
        return inlineSetter(context, input, STORED_DATA, CURRENT_CSV);
    }

    public static String getCurrentCSV(@NonNull Context context) {
        return inlineGetter(context, STORED_DATA, CURRENT_CSV);
    }

    public static Boolean RegexCheck(String input) {
        return Pattern.matches(REGEX, input);
    }
}
