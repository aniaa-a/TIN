class Router {
    constructor(app) {
        this.app = app;
        this.routes = [];

        this.hashChange = this.hashChange.bind(this);
        window.addEventListener('hashchange', this.hashChange);
    }
    addRoute(name, url, needAuthorization) {
        this.routes.push({name, url, needAuthorization});
    }
    hashChange() {
        const hash = window.location.hash;
        const route = this.routes.filter(route => hash.match(new RegExp(route.url)))[0];

        if (route) {
            if (route.needAuthorization) {
                this.app.showComponentWithAuthorization(route.name);
            } else {
                this.app.showComponent(route.name);
            }
        }
    }
}
