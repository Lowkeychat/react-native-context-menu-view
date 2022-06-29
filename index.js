import React from "react";
import {requireNativeComponent} from "react-native";

const NativeContextMenu = requireNativeComponent("ContextMenu");

const ContextMenu = (props) => {
  return (
      <NativeContextMenu
          title={props.title}
          actions={props.actions}
          onPress={props.onPress}
          onCancel={props.onCancel}
          previewBackgroundColor={props.previewBackgroundColor}
          dropdownMenuMode={props.dropdownMenuMode}
          previewSourceUri={props.previewSourceUri}
          previewSize={props.previewSize}
      >
        {props.children}
      </NativeContextMenu>
  );
};

export default ContextMenu;
