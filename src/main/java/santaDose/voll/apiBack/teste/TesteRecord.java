package santaDose.voll.apiBack.teste;

public record TesteRecord(String logradouro, String bairro, String cep, String cidade, String uf, String numero) {

    //Também da pra fazer isso com enum por exemplo, se for escolher entre 3 tipos, por exemplo arroz, feijao, carne
    //Você cria o enum, e invez de passar os valores igual faz com o record, você cria eles que nem json
    //"Arroz",
    //"Feijão",
    //"Carne"
}
