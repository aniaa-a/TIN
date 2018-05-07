class Trips {
    constructor(selector) {
        this.element = document.querySelector(selector);
        this.trips = [];
        this.tripsLoaded = new AppEvent(this);
    }

    loadTrips() {
        const url = '/tin/trip/getAll';

        fetch(url)
            .then(response => {
                if (response.status === 200) {
                    return Promise.resolve(response);
                } else {
                    return Promise.reject(new Error(response.statusText));
                }
            })
            .then(response => response.json())
            .then(data => this.setTrips(data))
            .catch(error => alert(error));
    }

    setTrips(trips) {
        this.trips = trips;
        this.tripsLoaded.notify(trips);
    }

    updateView() {
        this.element.innerHTML = Trips.getTemplate(this.trips);
    }

    static getTemplate(trips) {
        let template = '';

        trips.forEach(trip => {
            template += `<li><a href="#/trip/${trip.tripId}">${trip.city}</a></li>`;
        });

        return template;
    }
}
