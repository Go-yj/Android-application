package com.example.android_application.presentation.Search;


import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.android_application.MainActivity;
import com.example.android_application.R;
import com.example.android_application.presentation.Home.OnBackPressedListener;

import java.util.ArrayList;
import java.util.Arrays;

public class SearchFragment extends Fragment implements OnBackPressedListener {

    private View view;
    private SearchData searchData;

    private EditText search;
    private Button searchButton;
    //private MenuItem mSearch;
    //private SearchView searchView;
    private String search_word;
    private String type = "all";
    private ArrayList<String> genre= new ArrayList<>();
    private String start_date = "0000-00-00";
    private String end_date = "0000-00-00";

    private Button type_movie;
    private Button type_drama;
    private Button genre_action;
    private Button genre_comedy;
    private Button genre_fantasy;
    private Button genre_animation;
    private Button genre_adventure;
    private Button genre_drama;
    private Button genre_crime;
    private Button genre_war;
    private Button genre_family;
    private Button genre_mystery;
    private Button genre_documentary;
    private Button genre_science;
    private Button genre_western;
    private LinearLayout movie_option1;
    private LinearLayout movie_option2;
    private Button genre_history;
    private Button genre_horror;
    private Button genre_music;
    private Button genre_romance;
    private Button genre_thriller;
    private Button genre_tv;

    private Boolean[] clicked = new Boolean[19];
    private Boolean type_movie_clicked=false;
    private Boolean type_drama_clicked=false;

    private Spinner start_year;
    private Spinner end_year;
    private Spinner start_month;
    private Spinner end_month;
    private String startYearString="0000";
    private int startPosition;
    private String startMonthString="00";
    private String endYearString="0000";
    private int endPosition;
    private String endMonthString="00";
    private int endMonthInt;


    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag2, container, false);

        search = (EditText) view.findViewById(R.id.searchView);
        searchButton = (Button) view.findViewById(R.id.searchButton);
        type_movie = (Button) view.findViewById(R.id.type_movie_button);
        type_drama = (Button) view.findViewById(R.id.type_drama_button);
        genre_action = (Button) view.findViewById(R.id.genre_action_button);
        genre_comedy = (Button) view.findViewById(R.id.genre_comedy_button);
        genre_fantasy = (Button) view.findViewById(R.id.genre_fantasy_button);
        genre_animation= (Button) view.findViewById(R.id.genre_animation_button);
        genre_adventure = (Button) view.findViewById(R.id.genre_adventure_button);
        genre_drama = (Button) view.findViewById(R.id.genre_drama_button);
        genre_crime = (Button) view.findViewById(R.id.genre_crime_button);
        genre_war = (Button) view.findViewById(R.id.genre_war_button);
        genre_family = (Button) view.findViewById(R.id.genre_family_button);
        genre_mystery = (Button) view.findViewById(R.id.genre_mystery_button);
        genre_documentary = (Button) view.findViewById(R.id.genre_documentary_button);
        genre_science = (Button) view.findViewById(R.id.genre_science_button);
        genre_western = (Button) view.findViewById(R.id.genre_western_button);
        genre_history = (Button) view.findViewById(R.id.genre_history_button);
        genre_horror = (Button) view.findViewById(R.id.genre_horror_button);
        genre_music = (Button) view.findViewById(R.id.genre_music_button);
        genre_romance = (Button) view.findViewById(R.id.genre_romance_button);
        genre_thriller = (Button) view.findViewById(R.id.genre_thriller_button);
        genre_tv = (Button) view.findViewById(R.id.genre_tv_button);
        movie_option1 = (LinearLayout) view.findViewById(R.id.movie_option1);
        movie_option2 = (LinearLayout) view.findViewById(R.id.movie_option2);
        start_year = (Spinner) view.findViewById(R.id.start_year);
        start_month = (Spinner) view.findViewById(R.id.start_month);
        end_year = (Spinner) view.findViewById(R.id.end_year);
        end_month = (Spinner) view.findViewById(R.id.end_month);

        Arrays.fill(clicked, false);

        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                switch(actionId) {
                    case EditorInfo.IME_ACTION_SEARCH:
                        searchButton.callOnClick();
                }
                return false;
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search_word = search.getText().toString();
                if (type != "movie") {
                    genre.remove("history");
                    genre.remove("horror");
                    genre.remove("music");
                    genre.remove("romance");
                    genre.remove("thriller");
                    genre.remove("tv");
                    genre_history.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonUnClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    genre_horror.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonUnClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    genre_music.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonUnClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    genre_romance.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonUnClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    genre_thriller.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonUnClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    genre_tv.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonUnClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    for (int i=13 ; i<=18 ; i++) {
                        clicked[i] = false;
                    }
                }
                start_date = startYearString+"-"+startMonthString+"-01";
                switch(endMonthInt) {
                    case 0:
                        end_date = endYearString+"-"+endMonthString+"-00";
                        break;
                    case 2:
                        end_date = endYearString+"-"+endMonthString+"-28";
                        break;
                    case 4:
                    case 6:
                    case 9:
                    case 11:
                        end_date = endYearString+"-"+endMonthString+"-30";
                        break;
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                        end_date = endYearString+"-"+endMonthString+"-31";
                        break;
                }
                searchData = new SearchData(type, search_word, genre, start_date, end_date);
            }
        });

        type_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type_movie_clicked) {
                    type_movie.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonUnClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    type="all";
                    type_movie_clicked = false;
                    movie_option1.setVisibility(View.INVISIBLE);
                    movie_option2.setVisibility(View.GONE);
                } else {
                    type = "movie";
                    type_movie.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    type_movie_clicked=true;
                    movie_option1.setVisibility(View.VISIBLE);
                    movie_option2.setVisibility(View.VISIBLE);
                }
                if (type_drama_clicked) {
                    type_drama.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonUnClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    type_drama_clicked = false;
                }
            }
        });

        type_drama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type_drama_clicked) {
                    type_drama.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonUnClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    type="all";
                    type_drama_clicked = false;
                } else {
                    type = "drama";
                    type_drama.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    type_drama_clicked=true;
                }
                if (type_movie_clicked) {
                    type_movie.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonUnClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    type_movie_clicked = false;
                    movie_option1.setVisibility(View.INVISIBLE);
                    movie_option2.setVisibility(View.GONE);
                }
            }
        });


        genre_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clicked[0]) {
                    genre_action.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonUnClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    clicked[0] =false;
                    genre.remove("action");
                } else {
                    genre_action.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    clicked[0]=true;
                    genre.add("action");
                }
            }
        });

        genre_comedy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clicked[1]) {
                    genre_comedy.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonUnClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    clicked[1]=false;
                    genre.remove("comedy");
                } else {
                    genre_comedy.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    clicked[1]=true;
                    genre.add("comedy");
                }
            }
        });

        genre_fantasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clicked[2]) {
                    genre_fantasy.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonUnClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    clicked[2]=false;
                    genre.remove("fantasy");
                } else {
                    genre_fantasy.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    clicked[2]=true;
                    genre.add("fantasy");
                }
            }
        });

        genre_animation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clicked[3]) {
                    genre_animation.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonUnClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    clicked[3]=false;
                    genre.remove("animation");
                } else {
                    genre_animation.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    clicked[3]=true;
                    genre.add("animation");
                }
            }
        });

        genre_adventure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clicked[4]) {
                    genre_adventure.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonUnClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    clicked[4]=false;
                    genre.remove("adventure");
                } else {
                    genre_adventure.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    clicked[4]=true;
                    genre.add("adventure");
                }
            }
        });

        genre_drama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clicked[5]) {
                    genre_drama.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonUnClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    clicked[5]=false;
                    genre.remove("drama");
                } else {
                    genre_drama.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    clicked[5]=true;
                    genre.add("drama");
                }
            }
        });

        genre_crime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clicked[6]) {
                    genre_crime.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonUnClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    clicked[6]=false;
                    genre.remove("crime");
                } else {
                    genre_crime.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    clicked[6]=true;
                    genre.add("crime");
                }
            }
        });

        genre_war.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clicked[7]) {
                    genre_war.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonUnClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    clicked[7]=false;
                    genre.remove("war");
                } else {
                    genre_war.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    clicked[7]=true;
                    genre.add("war");
                }
            }
        });

        genre_family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clicked[8]) {
                    genre_family.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonUnClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    clicked[8]=false;
                    genre.remove("family");
                } else {
                    genre_family.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    clicked[8]=true;
                    genre.add("family");
                }
            }
        });

        genre_mystery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clicked[9]) {
                    genre_mystery.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonUnClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    clicked[9]=false;
                    genre.remove("mystery");
                } else {
                    genre_mystery.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    clicked[9]=true;
                    genre.add("mystery");
                }
            }
        });

        genre_documentary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clicked[10]) {
                    genre_documentary.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonUnClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    clicked[10]=false;
                    genre.remove("documentary");
                } else {
                    genre_documentary.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    clicked[10]=true;
                    genre.add("documentary");
                }
            }
        });

        genre_science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clicked[11]) {
                    genre_science.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonUnClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    clicked[11]=false;
                    genre.remove("science");
                } else {
                    genre_science.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    clicked[11]=true;
                    genre.add("science");
                }
            }
        });

        genre_western.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clicked[12]) {
                    genre_western.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonUnClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    clicked[12]=false;
                    genre.remove("western");
                } else {
                    genre_western.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    clicked[12]=true;
                    genre.add("western");
                }
            }
        });

        genre_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clicked[13]) {
                    genre_history.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonUnClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    clicked[13]=false;
                    genre.remove("history");
                } else {
                    genre_history.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    clicked[13]=true;
                    genre.add("history");
                }
            }
        });

        genre_horror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clicked[14]) {
                    genre_horror.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonUnClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    clicked[14]=false;
                    genre.remove("horror");
                } else {
                    genre_horror.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    clicked[14]=true;
                    genre.add("horror");
                }
            }
        });

        genre_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clicked[15]) {
                    genre_music.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonUnClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    clicked[15]=false;
                    genre.remove("music");
                } else {
                    genre_music.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    clicked[15]=true;
                    genre.add("music");
                }
            }
        });

        genre_romance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clicked[16]) {
                    genre_romance.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonUnClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    clicked[16]=false;
                    genre.remove("romance");
                } else {
                    genre_romance.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    clicked[16]=true;
                    genre.add("romance");
                }
            }
        });

        genre_thriller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clicked[17]) {
                    genre_thriller.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonUnClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    clicked[17]=false;
                    genre.remove("thriller");
                } else {
                    genre_thriller.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    clicked[17]=true;
                    genre.add("thriller");
                }
            }
        });

        genre_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clicked[18]) {
                    genre_tv.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonUnClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    clicked[18]=false;
                    genre.remove("tv");
                } else {
                    genre_tv.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.buttonClicked),
                            PorterDuff.Mode.SRC_ATOP);
                    clicked[18]=true;
                    genre.add("tv");
                }
            }
        });

        start_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    startYearString = (String)parent.getItemAtPosition(position);
                } else {
                    startYearString = "0000";
                    start_month.setSelection(0);
                    startMonthString="00";
                }
                startPosition = position;
                if (endPosition<position) {
                    end_year.setSelection(0);
                    end_month.setSelection(0);
                    endYearString = "0000";
                    endMonthString = "00";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        start_month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (startYearString.equals("0000")) {
                    start_month.setSelection(0);
                    startMonthString = "00";
                } else {
                    if (position != 0) {
                        startMonthString = (String)parent.getItemAtPosition(position);
                    } else {
                        startMonthString = "00";
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        end_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position < startPosition) {
                    Toast.makeText(getActivity(), "시작년도가 더 빨라야 합니다.", Toast.LENGTH_SHORT).show();
                    end_year.setSelection(0);
                    endYearString = "0000";
                } else {
                    endYearString = (String)parent.getItemAtPosition(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        end_month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (endYearString.equals("0000")) {
                    end_month.setSelection(0);
                    endMonthString = "00";
                } else {
                    if (position != 0) {
                        endMonthString = (String)parent.getItemAtPosition(position);
                    } else {
                        endMonthString = "00";
                    }
                }
                endMonthInt = Integer.parseInt(endMonthString);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }

    @Override
    public void onBack() {
        ((MainActivity)getActivity()).finish();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((MainActivity)context).setOnBackPressedListener(this);
    }
}