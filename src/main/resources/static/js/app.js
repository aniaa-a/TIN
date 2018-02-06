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
            alert('Aby zlozyc rezerwacje.. \nZaloguj sie pytam sie!\nAle to juz!');
        }
    }
    updateView(template) {
        this.container.innerHTML = template;
    }
}
