package com.mpiannucci.reactnativecontextmenu;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class ContextMenuManager extends ViewGroupManager<ContextMenuView> {

    public static final String REACT_CLASS = "ContextMenu";
    public static final int SHOW_MENU = 1;

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    public ContextMenuView createViewInstance(ThemedReactContext context) {
        ContextMenuView reactViewGroup = new ContextMenuView(context);
        return reactViewGroup;
    }

    @ReactProp(name = "title")
    public void setTitle(ContextMenuView view, @Nullable String title) {
        // TODO: Maybe support this? IDK if its necessary though
    }

    @ReactProp(name = "actions")
    public void setActions(ContextMenuView view, @Nullable ReadableArray actions) {
        view.setActions(actions);
    }

    @ReactProp(name = "dropdownMenuMode")
    public void setDropdownMenuMode(ContextMenuView view, @Nullable boolean enabled) {
        view.setDropdownMenuMode(enabled);
    }

    @androidx.annotation.Nullable
    @Override
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.<String, Object>builder()
                .put("onPress", MapBuilder.of("registrationName", "onPress"))
                .put("onCancel", MapBuilder.of("registrationName", "onCancel"))
                .build();
    }

    @Override
    public void receiveCommand(@NonNull ContextMenuView view, int commandId, @Nullable ReadableArray args) {
        super.receiveCommand(view, commandId, args);
        switch (commandId) {
            case SHOW_MENU:
                view.showMenu();
                return;
            default:
                throw new IllegalArgumentException(String.format(
                        "Unsupported command %d received by %s.",
                        commandId,
                        getClass().getSimpleName()));
        }
    }

    @Nullable
    @Override
    public Map<String, Integer> getCommandsMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("showMenu", SHOW_MENU);

        return map;
    }
}
