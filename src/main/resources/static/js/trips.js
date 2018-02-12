class Trips {
    constructor(selector) {
        this.form = document.querySelector(selector);
        this.trips = [];
        this.tripsLoaded = new AppEvent(this);
    }

    loadTrips() {
        const url = '/trip/getAll';

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
        this.form.trips.innerHTML = Trips.getOptionsTemplate(this.trips);
    }

    getForm() {
        return this.form;
    }

    static getOptionsTemplate(trips) {
        let template = '';

        trips.forEach(trip => {
            template += `<option value="${trip.tripId}">${trip.city}</option>`;
        });

        return template;
    }
}
