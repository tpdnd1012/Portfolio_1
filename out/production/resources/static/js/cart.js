$(document).ready(function(){

    var money3 = 0;

    var moneyarr = $("input[name=money]").length;

    /*var moneyarr2 = new Array(moneyarr);*/

    for(var i = 0; i < moneyarr; i++) {
        /*moneyarr2[i] = $("input[name=money]").eq(i).val();*/
        money3 += parseInt($("input[name=money]").eq(i).val());
    }

    $("#final").text(money3.toLocaleString() + " 원");

});


function rentalchange(i) {

    /* 선택된 select의 금액 */
    var money = document.getElementsByName("money")[i].value;

    var money2 = new Array(money);

    var tmoney = document.getElementsByName("tmoney")[i].value;

    var s_val = document.getElementsByName("period")[i].value;

    var sum = 0;

    var moneyarr = $("input[name=tmoney]").length;

    if(s_val == 7) {

        tmoney = money;
        document.getElementsByName("tmoney")[i].value = money;

        money2 = money * 0.5;
        document.getElementsByName("rentalmoney")[i].innerHTML = (money2).toLocaleString() + "원";

        document.getElementsByName("tmoney")[i].value = money2;

        for(var i = 0; i < moneyarr; i++) {
            sum += parseInt($("input[name=tmoney]").eq(i).val());
        }
        $("#final").text(sum.toLocaleString() + " 원");

    }

    else if(s_val == 14) {

        tmoney = money;
        document.getElementsByName("tmoney")[i].value = money;

        money2 = money * 0.7;
        document.getElementsByName("rentalmoney")[i].innerHTML = (money2).toLocaleString() + "원";

        document.getElementsByName("tmoney")[i].value = money2;

        for(var i = 0; i < moneyarr; i++) {
            sum += parseInt($("input[name=tmoney]").eq(i).val());
        }
        $("#final").text(sum.toLocaleString() + " 원");

    }

    else if(s_val == 30) {

        tmoney = money;
        document.getElementsByName("tmoney")[i].value = money;

        money2 = money;
        document.getElementsByName("rentalmoney")[i].innerHTML = (money2).replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",") + " 원";

        document.getElementsByName("tmoney")[i].value = money2;

        for(var i = 0; i < moneyarr; i++) {
            sum += parseInt($("input[name=tmoney]").eq(i).val());
        }
        $("#final").text(sum.toLocaleString() + " 원");

    }

}
