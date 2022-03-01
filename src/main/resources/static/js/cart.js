$(document).ready(function(){

    var money3 = 0;

    var moneyarr = $("input[name=money]").length;

    /*var moneyarr2 = new Array(moneyarr);*/

    for(var i = 0; i < moneyarr; i++) {
        /*moneyarr2[i] = $("input[name=money]").eq(i).val();*/
        money3 += parseInt($("input[name=money]").eq(i).val());
    }

    $("#final").text(money3.toLocaleString() + " 원");

    /*$("#rentalselect").change(function(){

        var test = $("#pageName-rentalselect").val();

        %("#input[name=test]")

        var money = $(".classTest").text();

        var split = money.split("원");

        var won1 = split[0].replaceAll(",","");
        alert(won1);
        won1 = won1.replace("원","");

        var won2 = split[1].replaceAll(",","");
        won2 = won2.replace("원","");

        var sum = 0;
        sum = parseInt(won1) + parseInt(won2);

        var result = 0;

        if (test == 7) {
            result = sum * 0.5;
        }
        else if (test == 14) {
            result = sum * 0.7;
        }
        else {
            result = sum;
        }

        var temp = String(result);

        temp = temp.replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");

        $("#final").text(temp);

    });*/

});


function rentalchange(i) {

    /* 선택된 select의 금액 */
    var money = document.getElementsByName("money")[i].value;

    var money2 = new Array(money);

    /*var money5 = $("input[name='money']").length;

    for(var i = 0; i < money5; i++) {
        alert($("input[name='money']").eq(i).val());
    }*/

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

        document.getElementsByName("rentalmoney")[i].innerHTML = (money).toLocaleString() + " 원";

        document.getElementsByName("tmoney")[i].value = money2;

        for(var i = 0; i < moneyarr; i++) {
            sum += parseInt($("input[name=tmoney]").eq(i).val());
        }
        $("#final").text(sum.toLocaleString() + " 원");

    }

}
