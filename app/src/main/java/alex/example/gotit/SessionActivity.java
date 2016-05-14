package alex.example.gotit;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import java.util.List;

import alex.example.gotit.controls.VolleyInstance;
import alex.example.gotit.model.Feed;
import alex.example.gotit.model.FeedParser;
import alex.example.gotit.view.LogoutConfirmDialog;
import alex.example.gotit.view.adapter.FeedAdapter;

public class SessionActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, LogoutConfirmDialog.LogoutDialogListener {

    private ListView listFeeds;
    private FeedAdapter feedAdapter;
    private VolleyInstance instance;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "This function will be supported soon !", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        listFeeds = (ListView) findViewById(R.id.listView);
        feedAdapter = new FeedAdapter(this);
        listFeeds.setAdapter(feedAdapter);

        instance = VolleyInstance.getInstance(this);
        requestQueue = instance.getRequestQueue();

        performGetFeeds();
    }

    public void performGetFeeds() {
        String questionRequest = "http://54.241.128.111/questions";
        JsonArrayRequest getFeedRequest = new JsonArrayRequest(questionRequest, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<Feed> parse = FeedParser.parse(response);

                feedAdapter.addAll(parse);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("vuong.hong", "error");
            }
        });

        requestQueue.add(getFeedRequest);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            LogoutConfirmDialog logoutConfirmDialog = new LogoutConfirmDialog(this);
            logoutConfirmDialog.setListener(this);
            logoutConfirmDialog.show();
        }
    }

    @Override
    public void onLogout() {
        super.onBackPressed();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.session, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_askexpert) {
            // Handle the camera action
        } else if (id == R.id.nav_history) {

        } else if (id == R.id.nav_livefeed) {

        } else if (id == R.id.nav_notification) {

        } else if (id == R.id.nav_feedback) {

        } else if (id == R.id.nav_askcredits) {

        } else if (id == R.id.nav_rate) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
