var pseudo;
function Connexion(){
  var data = $('#connexionForm').serializeArray().reduce(function(obj, item) {
    obj[item.name] = item.value;
    return obj;
  }, {});
  $.ajax({
    type:"POST",
    url:"http://localhost:8080/motus/partie",
    contentType:"application/json; charset=utf-8",
    data:JSON.stringify(data),
    success: function(data,statut){
      pseudo=$("#pseudo").val();
      $("#connexionForm").empty();
      $("#items").empty();
      $("#info").empty();
      var item = "<label>Entrez un dictionnaire pour commencer à jouer : </label><input type=\"text\" name=\"dico\" id=\"dico\"/><button id=\"commencerPartie\">Lancer Partie</button><button id=\"voirDico\">Voir les dictionnaires</button>";
      $("#items").append(item);
      $("#info").append("Vous êtes connecté");
      var bouton = "<button id=\"relancer\">Retour choix du dico</button>";
      $("#boutons").append(bouton);
    },
    error:function(request,statut,error){
      $("#items").empty();
      $("#info").empty();
      $("#info").append(request.responseText);
    }
  });
};

$(document).ready(function(){
  $("#deconnexion").on("click",function(){
    $.ajax({
      type:"DELETE",
      url:"http://localhost:8080/motus/partie/"+pseudo,
      dataType:"text",
      success:function(data,statut){
        $("#items").empty();
        $("#info").empty();
        $("#info").append("Déconnecté(e)");
        var item="<label>Entrez votre pseudo pour vous Connecter : </label><input type=\"text\" name=\"pseudo\" id=\"pseudo\"/><input type=\"submit\" name=\"connexion\" id=\"connexion\" value=\"Connexion\"/>";
        $("#connexionForm").append(item);
        $("#relancer").remove();
      },
      error:function(data,statut,error){
        $("#items").empty();
        $("#info").empty();
        $("#info").append("Vous n'êtes pas connecté(e)");
        $("#relancer").remove();
      }
    });
  });

  $("#items").on("click","#voirDico",function(event){
    event.preventDefault();
    $.ajax({
      type:"GET",
      url:"http://localhost:8080/motus/dico",
      dataType:"json",
      success:function(data,statut){
        $("#info").empty();
        $("#info").append("Les dictionnaires disponible : ")
        $.each(data,function(key,val){
          $("#info").append(val+" ");
        });
      },
      error:function(data,statut,error){
        $("#items").append(error);
      }
    });
  });

  $("#items").on("click","#commencerPartie",function(event){
    event.preventDefault();
    $.ajax({
      type:"POST",
      url:"http://localhost:8080/motus/partie/"+pseudo,
      dataType:"text",
      data:$("#dico").serialize(),
      success:function(data,statut){
        $("#info").empty();
        $("#info").append(data);
        $("#items").empty();
        var item="<label>Entrez un mot : </label><input type=\"text\" name=\"mot\" id=\"mot\"/><button id=\"jouer\">Envoyer mot</button><button id=\"voirEssaies\">Voir les mots essayés</button>";
        $("#items").append(item);
      },
      error:function(data,statut,error){
        $("#info").empty();
        $("#info").append("Mauvais dictionnaire, choisissez-en un autre");
      }
    });
  });

  $("#items").on("click","#jouer",function(event){
    event.preventDefault();
    $.ajax({
      type:"PUT",
      url:"http://localhost:8080/motus/partie/"+pseudo,
      dataType:"text",
      data:$("#mot").serialize(),
      success:function(data,statut){
        $("#info").empty();
        $("#info").append(data);
      },
      error:function(request,statut,error){
        $("#info").empty();
        $("#info").append(request.responseText);
      }
    });
  });

  $("#items").on("click","#voirEssaies",function(event){
    event.preventDefault();
    $.ajax({
      type:"GET",
      url:"http://localhost:8080/motus/partie/"+pseudo+"/essaies",
      dataType:"text",
      success:function(data,statut){
        $("#info").empty();
        $("#info").append(data);
      },
      error:function(request,statut,error){
        $("#info").empty();
        $("#info").append(request.responseText);
      }
    });
  });

  $("#boutons").on("click","#relancer",function(event){
    event.preventDefault();
    $("#items").empty();
    $("#info").empty();
    var item = "<label>Entrez un dictionnaire pour commencer à jouer : </label><input type=\"text\" name=\"dico\" id=\"dico\"/><button id=\"commencerPartie\">Lancer Partie</button><button id=\"voirDico\">Voir les dictionnaires</button>";
    $("#items").append(item);
  });
});
