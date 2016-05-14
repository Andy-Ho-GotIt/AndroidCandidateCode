package alex.example.gotit.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import alex.example.gotit.controls.VolleyInstance;

/**
 * Created by Alex on 5/12/2016.
 */
public class LogoutConfirmDialog {

    private final AlertDialog dialog;
    private final Context context;
    private LogoutDialogListener listener;

    public interface LogoutDialogListener {
        void onLogout();
    }

    public void setListener(LogoutDialogListener listener) {
        this.listener = listener;
    }

    public LogoutConfirmDialog(Context context) {
        this.context = context;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Log out ?").setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sendLogoutRequest();
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog = builder.create();
    }

    public void show() {
        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }

    public void sendLogoutRequest() {
        final ProgressDialog loggingout = new ProgressDialog(context);
        loggingout.setMessage("Logging out");
        loggingout.setIndeterminate(true);
        loggingout.setCancelable(false);
        loggingout.show();

        String logoutUrl = "http://54.241.128.111/accounts/auth";

        StringRequest logoutReq = new StringRequest(Request.Method.DELETE, logoutUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("vuong.hong", "LOGOUT: " + response);
                Toast.makeText(context, "Logged out!", Toast.LENGTH_SHORT).show();
                loggingout.dismiss();
                if (listener != null)
                    listener.onLogout();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("vuong.hong", "LOGOUT Error: " + error);
                loggingout.dismiss();
                if (listener != null)
                    listener.onLogout();
            }
        });

        VolleyInstance instance = VolleyInstance.getInstance(context);
        RequestQueue requestQueue = instance.getRequestQueue();
        requestQueue.add(logoutReq);
    }
}
