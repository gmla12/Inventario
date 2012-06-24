function kadabra(zap) {
        if (document.getElementById) {
            var abra = document.getElementById(zap).style;
            if (abra.display == "block") {
                abra.display = "none";
            } else {
                abra.display = "block"
            }
            return false
        } else {
            return true
        }
    }
