package com.mpiannucci.reactnativecontextmenu;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.views.view.ReactViewGroup;

import javax.annotation.Nullable;

public class ContextMenuView extends ReactViewGroup implements PopupMenu.OnMenuItemClickListener, PopupMenu.OnDismissListener {

    PopupMenu contextMenu;

    boolean cancelled = true;

    protected boolean dropdownMenuMode = false;

    public ContextMenuView(final Context context) {
        super(context);

        contextMenu = new PopupMenu(getContext(), this);
        contextMenu.setOnMenuItemClickListener(this);
        contextMenu.setOnDismissListener(this);
    }

    @Override
    public void addView(View child, int index) {
        super.addView(child, index);

        child.setClickable(false);
    }

    public void setActions(@Nullable ReadableArray actions) {
        Menu menu = contextMenu.getMenu();
        menu.clear();

        for (int i = 0; i < actions.size(); i++) {
            ReadableMap action = actions.getMap(i);
            menu.add(Menu.NONE, Menu.NONE, i, action.getString("title"));
            menu.getItem(i).setEnabled(!action.hasKey("disabled") || !action.getBoolean("disabled"));
        }
    }

    public void setDropdownMenuMode(@Nullable boolean enabled) {
        this.dropdownMenuMode = enabled;
    }

    public void showMenu() {
        contextMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        cancelled = false;
        ReactContext reactContext = (ReactContext) getContext();
        WritableMap event = Arguments.createMap();
        event.putInt("index", menuItem.getOrder());
        event.putString("name", menuItem.getTitle().toString());
        reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(getId(), "onPress", event);
        return false;
    }

    @Override
    public void onDismiss(PopupMenu popupMenu) {
        if (cancelled) {
            ReactContext reactContext = (ReactContext) getContext();
            reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(getId(), "onCancel", null);
        }

        cancelled = true;
    }
}
