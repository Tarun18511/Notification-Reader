package messages.gohool.messages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button showButton;
    private SharedPreferences pref;
    public static final String SHARED_PREFERENCE2 = "SharedPreferences";
    public static final String KEY = "Messages";
    EditText allMessagesET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showButton = (Button) findViewById(R.id.showButton);
        allMessagesET = (EditText) findViewById(R.id.allMessageET);
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pref = getSharedPreferences(SHARED_PREFERENCE2, 0);
                String name = pref.getString(KEY, "default value");
                allMessagesET.setText(name);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString(KEY, "");
                editor.commit();
            }
        });
    }

}
