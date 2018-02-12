class App {
    constructor(selector, model) {
        const self = this;

        this.model = model;
        this.container = document.querySelector(selector);
        this.components = {};
        this.currentComponent = {};

        model.templateLoaded.attach(function(template) {
            self.updateView(template);
            if (self.currentComponent.controller) {
                self.currentComponent.controller();
            }
        });
    }
    addComponent(component) {
        this.components[component.name] = component;
    }
    showComponent(name) {
        this.currentComponent = this.components[name];
        if (this.currentComponent) {
            this.model.getTemplate(this.currentComponent.templateUrl);
        } else {
            this.updateView('<h2>Not Found (404)</h2>')
        }
    }
    showComponentWithAuthorization(name, authorization) {
        if (authorization.scope === 'admin') {
            if (this.model.authorizationInfo.isAdmin) {
                this.showComponent(name);
            } else {
                location.hash = '/home';
                alert('Nie posiadasz praw admina');
            }
        } else {
            if (this.model.authorizationInfo.isLogged) {
                this.showComponent(name);
            } else {
                location.hash = '/home';
                alert('Zaloguj si\u0119');
            }
        }
    }
    updateView(template) {
        this.container.innerHTML = template;
    }
}
