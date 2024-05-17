package br.com.leaf.clientes.mappers;

import br.com.leaf.clientes.DominioSituacao;
import br.com.leaf.clientes.dtos.AtualizaClienteRequestDTO;
import br.com.leaf.clientes.dtos.ClienteDTO;
import br.com.leaf.clientes.dtos.CriaClienteRequestDTO;
import br.com.leaf.clientes.models.Clientes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static br.com.leaf.clientes.DominioSituacao.ATIVO;
import static br.com.leaf.clientes.DominioSituacao.INATIVO;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Mapper(componentModel = "spring")
public interface ClientesMapper {

    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "cpf", source = "cpf")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "dtNascimento", source = "dtNascimento")
    Clientes criaClienteRequestDTOToClienteEntity(CriaClienteRequestDTO cliente);

    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "dtNascimento", source = "dtNascimento")
    Clientes atualizaClienteRequestDTOToClienteEntity(AtualizaClienteRequestDTO cliente);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "cpf", source = "cpf")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "dtNascimento", source = "dtNascimento")
    @Mapping(target = "situacao", expression = "java(montarSituacao(cliente.getSituacao()))")
    ClienteDTO clienteTOClienteDTO(Clientes cliente);

    default Boolean montarSituacaoDominio(DominioSituacao situacao) {
        return situacao.equals(ATIVO) ? TRUE : FALSE;
    }

    default DominioSituacao montarSituacao(Boolean situacao) {
        return situacao.booleanValue()? ATIVO : INATIVO;
    }

}
