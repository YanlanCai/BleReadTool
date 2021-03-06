package adapters;

import android.util.Log;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.Vector;

import bt.lcy.btread.BtStaticVal;
import bt.lcy.btread.R;
import bt.lcy.btread.TiMsp432ProjectZeroActivity;
import bt.lcy.btread.fragments.ButtonFragment;
import bt.lcy.btread.fragments.IotSensor;
import bt.lcy.btread.fragments.LedRgbPicker;
import bt.lcy.btread.fragments.StringStream;

// example https://androidwave.com/viewpager2-with-fragments-android-example/

public class ViewPagerFragmentAdapter extends FragmentStateAdapter {
    private static final int TAB_SIZE = 4;
    private Fragment[] tabs;
    TiMsp432ProjectZeroActivity parentActivity;
    private static final int[] icons = {R.drawable.ic_settings_input_antenna_24px, R.drawable.ic_music_note_24px,
            R.drawable.ic_wb_incandescent_24px, R.drawable.ic_edit_24px};
    private static final int[] titles = {R.string.text_IOT, R.string.text_BUTTON, R.string.text_LED, R.string.text_DATA};

    public ViewPagerFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        parentActivity = (TiMsp432ProjectZeroActivity)fragmentActivity;
        tabs = new Fragment[TAB_SIZE];
        int i = 0;

    }
    public int getIcon(@LayoutRes int position) {
        return icons[position];
    }

    public int getText(int position) {
        return titles[position];
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                IotSensor  iotSensor  = new IotSensor(parentActivity.getGattManager(),parentActivity.getDevice());
                return iotSensor;
            case 1:

                ButtonFragment buttonFragment = new ButtonFragment(parentActivity.getGattManager(),parentActivity.getDevice());
                return buttonFragment;
            case 2:

                LedRgbPicker  ledRgbPicker= new LedRgbPicker(parentActivity.getGattManager(),parentActivity.getDevice());
                return ledRgbPicker;
            case 3:
                 StringStream stringStream = new StringStream(parentActivity.getGattManager(),parentActivity.getDevice());
                return stringStream;

        }
        return new LedRgbPicker(parentActivity.getGattManager(),parentActivity.getDevice());
    }

    @Override
    public int getItemCount() {
        return TAB_SIZE;
    }


}