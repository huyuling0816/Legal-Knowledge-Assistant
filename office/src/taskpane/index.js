import App from "./components/App";
import { AppContainer } from "react-hot-loader";
import { initializeIcons } from "@fluentui/font-icons-mdl2";
import * as React from "react";
import * as ReactDOM from "react-dom";
/* global document, Office, module, require */
import "element-theme-default";
import { Provider } from "react-redux";
import store from "../store/store";

window.history.replaceState = window._historyCache.replaceState;
window.history.pushState = window._historyCache.pushState;
initializeIcons();

let isOfficeInitialized = false;

const title = "Contoso Task Pane Add-in";

const render = (Component) => {
  ReactDOM.render(
    <Provider store={store}>
      <React.StrictMode>
        <Component title={title} isOfficeInitialized={isOfficeInitialized} />
      </React.StrictMode>
    </Provider>,
    document.getElementById("container")
  );
};

/* Render application after Office initializes */
Office.onReady(() => {
  isOfficeInitialized = true;
  render(App);
});

/* Initial render showing a progress bar */
render(App);

if (module.hot) {
  module.hot.accept("./components/App", () => {
    const NextApp = require("./components/App").default;
    render(NextApp);
  });
}
