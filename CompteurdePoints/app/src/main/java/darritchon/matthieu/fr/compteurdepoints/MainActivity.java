package darritchon.matthieu.fr.compteurdepoints;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String TEAM_1_SCORE = "team1Score";
    public static final String TEAM_2_SCORE = "team2Score";
    private TextView tvZoneScore1;
    private TextView tvZoneScore2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String team1Score = "0";

        View blocScore1 = findViewById(R.id.bloc_score_team1);
        if (savedInstanceState != null) {
            team1Score = savedInstanceState.getString(TEAM_1_SCORE);
        }
        tvZoneScore1 = initializeBlocScore(blocScore1, "Equipe 1",team1Score);

        String team2Score = "0";
        View blocScore2 = findViewById(R.id.bloc_score_team2);
        if (savedInstanceState != null) {
            team2Score = savedInstanceState.getString(TEAM_2_SCORE);
        }
        tvZoneScore2 = initializeBlocScore(blocScore2, "Equipe 2",team2Score);

        Button buttonReset = (Button) this.findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                int newvaleurScore = 0;
                tvZoneScore1.setText(String.valueOf(newvaleurScore));
                tvZoneScore2.setText(String.valueOf(newvaleurScore));
            }
        });

    } //fin de méthode

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        final TextView tvZoneScore1 = (TextView) this.tvZoneScore1.findViewById(R.id.bloc_score_tv_zone_affichage);
        outState.putString(TEAM_1_SCORE, tvZoneScore1.getText().toString());
        final TextView tvZoneScore2 = (TextView) this.tvZoneScore2.findViewById(R.id.bloc_score_tv_zone_affichage);
        outState.putString(TEAM_2_SCORE, tvZoneScore2.getText().toString());
        super.onSaveInstanceState(outState);
    }

    private TextView initializeBlocScore(View blocScore, String teamName, String teamScore) {
        final TextView tvTeamName = (TextView) blocScore.findViewById(R.id.bloc_score_tv_teamname);
        tvTeamName.setText(teamName);

        final TextView tvZoneScore = (TextView) blocScore.findViewById(R.id.bloc_score_tv_zone_affichage);
        tvZoneScore.setText(teamScore);

        Button buttonTeamAdd = (Button) blocScore.findViewById(R.id.bloc_score_bv_add1);
        buttonTeamAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                addPoint(tvZoneScore);
            }
        });

        Button buttonTeamRemove = (Button) blocScore.findViewById(R.id.bloc_score_bv_remove1);
        buttonTeamRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                removePoint(tvZoneScore);
            }
        });
        return tvZoneScore;
    }

    private void removePoint(TextView tvZoneScore) {
        int valeurScore = Integer.parseInt(tvZoneScore.getText().toString());
        int newvaleurScore = valeurScore - 1;
        tvZoneScore.setText(String.valueOf(newvaleurScore));
    }

    private void addPoint(TextView tvZoneScore) {
        int valeurScore = Integer.parseInt(tvZoneScore.getText().toString());
        int newvaleurScore = valeurScore + 1;
        tvZoneScore.setText(String.valueOf(newvaleurScore));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


}
