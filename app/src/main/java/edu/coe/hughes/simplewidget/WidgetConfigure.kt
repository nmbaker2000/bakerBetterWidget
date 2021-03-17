package edu.coe.hughes.simplewidget

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class WidgetConfigure : AppCompatActivity(), View.OnClickListener {
    var mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID

    /** Called when the activity is first created.  */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Find the widget id from the intent.
        val intent: Intent = getIntent()
        val extras = intent.extras
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID)
        }
        setContentView(R.layout.configure_layout)
        findViewById<Button>(R.id.btnSave).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val context: Context = this@WidgetConfigure

        // Push widget update to surface with newly set prefix
        val appWidgetManager = AppWidgetManager.getInstance(context)
        // RandomWidget.updateTitle(context, appWidgetManager, mAppWidgetId, "");
        val title = findViewById<TextView>(R.id.cfgTitle).text.toString()
        Prefs.savePref(context, mAppWidgetId, title);

        //Trigger an update
        val intent = Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE, null, this, RandomWidget::class.java)
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, intArrayOf(mAppWidgetId))
        sendBroadcast(intent)

        // Make sure we pass back the original appWidgetId


        val resultValue = Intent()
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId)
        setResult(RESULT_OK, resultValue)
        finish()
    }
}

