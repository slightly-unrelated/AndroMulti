package java.main.AndroMulti.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.main.AndroMulti.R;
import java.main.AndroMulti.adapter.NFCReader;
import java.main.AndroMulti.utils.ReaderTask;
import java.util.Objects;

public class MainFragment extends Fragment {

    private static final String TAG = "MainFragment";
    private MainViewModel mViewModel;
    private ReaderTask readerTask;
    private Context mContext;
    Button addIDButton;
    TextInputLayout IDTextbox;


    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mContext = container.getContext();
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        NFCReader nfcReader = new NFCReader();
        addIDButton = view.findViewById(R.id.addIDButton);
        IDTextbox = view.findViewById(R.id.IDTextbox);

        readerTask = nfcReader.init(getContext());

        addIDButton.setOnClickListener(view1 -> {
            String message = "" +
                    SharedPrefIO.getStoredCardID(mContext);
            Log.d(TAG, message);

            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
        });
    }

    public void onViewCallback() {
        IDTextbox.getEditText().setText(SharedPrefIO.getStoredCardID(mContext));
    }

}