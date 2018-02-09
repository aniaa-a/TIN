class App {
    constructor(selector, model) {
        const self = this;

        this.model = model;
        this.container = document.querySelector(selector);
        this.components = {};

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
        this.model.getTemplate(this.currentComponent.templateUrl);
    }
    showComponentWithAuthorization(name) {
        if (this.model.isLogged) {
            this.showComponent(name);
        } else {
            window.history.back();
            alert('Musisz by\u0107 zalogowany aby z\u0142o\u017Cy\u0107 rezerwacj\u0119');
        }
    }
    updateView(template) {
        this.container.innerHTML = template;
    }
}
