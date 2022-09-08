package java.main.AndroMulti.adapter;

import android.content.Context;
import android.nfc.NfcAdapter;
import android.widget.Toast;

import java.main.AndroMulti.utils.ReaderTask;

public class NFCReader {

    private NfcAdapter nfcAdapter;
    private ReaderTask readerTask;

    /**
     * Initializes NFCAdapter
     * @param context Context of activity
     * @return Success status
     */

    public ReaderTask init (Context context){
        nfcAdapter = NfcAdapter.getDefaultAdapter(context);
        if (nfcAdapter != null){
            readerTask = new ReaderTask();
            return readerTask;
        }
        return null;
    }

}
