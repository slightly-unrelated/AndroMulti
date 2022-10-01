package java.main.AndroMulti.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.nfc.tech.IsoDep;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.Ndef;
import android.nfc.tech.NfcA;
import android.nfc.tech.NfcB;
import android.nfc.tech.NfcF;
import android.nfc.tech.NfcV;
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

import java.main.AndroMulti.R;
import java.main.AndroMulti.adapter.NFCReader;
import java.main.AndroMulti.utils.ReaderTask;
import java.util.Objects;

public class MainFragment extends Fragment {

    private static final String TAG = "MainFragment";
    private static final String STORED_DATA = "NFC_DATA";
    private MainViewModel mViewModel;
    private ReaderTask readerTask;
    Button testButton;



    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        NFCReader nfcReader = new NFCReader();
        testButton = view.findViewById(R.id.button);



        readerTask = nfcReader.init(getContext());

        testButton.setOnClickListener(view1 -> {
            Log.d(TAG, "onViewCreated: DONE");
            String toastMessage = "";
            requireActivity().getPreferences(Context.MODE_PRIVATE).getString(STORED_DATA, toastMessage);

            Log.d(TAG, toastMessage);

            Toast.makeText(getContext(), toastMessage, Toast.LENGTH_LONG).show();

        });


    }

}