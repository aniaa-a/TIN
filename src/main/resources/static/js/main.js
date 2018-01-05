(function(){
    const navRoutes = {
        home: 'templates/home.html',
        cracow: 'templates/krakow.html',
        zakopane: 'templates/zakopane.html',
        warsaw: 'templates/warszawa.html',
        contact: 'templates/contact.html',
        signIn: 'templates/logowanie.html'
    };

    let container = document.getElementById('container'),
        isLogged = false;

    bindEvents();
    setNavigation();

    function bindEvents() {
        let signOutBtn = document.getElementById('sign-out');

        signOutBtn.onclick = function() {
            isLogged = false;
            setNavigation();
        };

        document.body.onclick = function(event) {
            if (event.target.id === 'reservationButton') {
                alert("Rezerwacja dla: " + event.target.dataset.reservation);
            } else if (event.target.id === 'sign-in-submit') {
                signIn(event);
            }
        };

        window.onhashchange = function() {
            if (location.hash === '') {
                location.hash = 'home';
            }
            ajax(navRoutes[location.hash.substring(1)], replaceHtml);
        };

        window.dispatchEvent(new Event('hashchange'));
    }

    function ajax(url, callback) {
        let xhr = new XMLHttpRequest();

        xhr.onload = function() {
            if (this.status === 200) {
                callback(this.response);
            }
        };

        xhr.open('GET', url, true);
        xhr.send();
    }

    function replaceHtml(data) {
        container.innerHTML = data;
    }

    function setNavigation() {
        let navAccess = document.querySelectorAll('.nav-access')[0],
            navSignOut = document.querySelectorAll('.sign-out')[0];

        navAccess.hidden = isLogged;
        navSignOut.hidden = !isLogged;
    }

    function signIn(event) {
        let emailInput = document.querySelectorAll('input[name=email]')[0],
            login = document.querySelectorAll('.user-login')[0];

        event.preventDefault();
        isLogged = true;
        login.innerHTML = emailInput.value;
        setNavigation();
        location.hash = '#home';
    }

})();