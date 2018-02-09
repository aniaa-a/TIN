(function() {
    const admin = new AdminModel();
    const service = new AdminService();
    const popup = new AdminEditPopup('.popup');
    const editForm = document.querySelector('#editForm');

    admin.getReservations(getReservationsSuccessHandler);
    admin.getDrivers(getDriversSuccessHandler);
    admin.getCars(getCarsSuccessHandler);

    editForm.addEventListener('submit', function(e) {
        e.preventDefault();

        const data = {
            id_reservation: admin.currentEdit
        };

        alert(`ID rezerwacji: ${admin.currentEdit}\nID kierowcy : ${this.drivers.value}\nID samochodu: ${this.cars.value}`);
    });

    function getReservationsSuccessHandler(reservations) {
        const container = document.querySelector('#container');
        const table = service.getTableTemplate(reservations);

        container.appendChild(table);
        bindEditClickEvent();
    }

    function getDriversSuccessHandler(drivers) {
        popup.setDrivers(drivers);
    }

    function getCarsSuccessHandler(cars) {
        popup.setCars(cars);
    }

    function removeTable() {
        const table = document.querySelector('#reservationsTable');

        table.parentElement.removeChild(table);
    }

    function bindEditClickEvent() {
        document.addEventListener('click', function(event) {
            if (event.target.dataset.role === 'edit') {
                admin.currentEdit = event.target.dataset.reservation;
                popup.show();
            }
        })
    }
})();
