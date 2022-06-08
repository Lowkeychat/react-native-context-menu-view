import React from "react";
import {requireNativeComponent} from "react-native";

const NativeContextMenu = requireNativeComponent("ContextMenu", null);

const ContextMenu = ({children, ...props}) => {
  return (
    <NativeContextMenu {props}>
      {children}
    </NativeContextMenu>
  );
};

export default ContextMenu;
