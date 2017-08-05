package com.baudiabatash.mygame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.baudiabatash.mygame.Layout.SudokuView;

public class SudokuActivity extends AppCompatActivity {
    private SudokuView sudokuView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sudokuView = new SudokuView(this);
        setContentView(sudokuView);
    }
}
