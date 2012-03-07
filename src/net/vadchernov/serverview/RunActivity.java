package net.vadchernov.serverview;
 
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
 
public class RunActivity extends Activity
{
	public static final String EXT_ID = "id";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.run);

        Bundle extras = getIntent().getExtras();
        String id = extras.getString(EXT_ID);
     
        TextView message = (TextView)findViewById(R.id.message);
        message.setText("Id: " + id);
    }
}