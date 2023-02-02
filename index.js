import React, {PureComponent} from "react";
import {requireNativeComponent, UIManager, findNodeHandle} from "react-native";

const NativeContextMenu = requireNativeComponent("ContextMenu");

export default class ContextMenu extends PureComponent {
    render() {
        return (
            <NativeContextMenu
                title={this.props.title}
                actions={this.props.actions}
                onPress={this.props.onPress}
                onCancel={this.props.onCancel}
                previewBackgroundColor={this.props.previewBackgroundColor}
                dropdownMenuMode={this.props.dropdownMenuMode}
                previewSourceUri={this.props.previewSourceUri}
                previewSize={this.props.previewSize}
            >
                {this.props.children}
            </NativeContextMenu>
        );
    }

    showMenu() {
        UIManager.dispatchViewManagerCommand(
            findNodeHandle(this),
            UIManager['ContextMenu'].Commands.showMenu,
            [],
        );
    }
}
