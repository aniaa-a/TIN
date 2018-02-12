class Router {
    constructor(app) {
        this.app = app;
        this.routes = [];
        this.params = [];

        this.hashChange = this.hashChange.bind(this);
        window.addEventListener('hashchange', this.hashChange);
    }
    addRoute(name, url, authorization) {
        this.routes.push({name, url, authorization});
    }
    hashChange() {
        const hash = window.location.hash;
        const route = this.routes.filter(route => hash.match(new RegExp(route.url)))[0];

        if (route) {
            this.params = new RegExp(route.url).exec(hash);

            if (route.authorization) {
                this.app.showComponentWithAuthorization(route.name, route.authorization);
            } else {
                this.app.showComponent(route.name);
            }
        } else {
            location.hash = '#/home'
        }
    }
    getParam() {
        return this.params[1];
    }
}
