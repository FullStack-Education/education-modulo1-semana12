package br.com.fullstack.education.m1s12.repository;

import br.com.fullstack.education.m1s12.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    // Para compor a pesquisa deve-se usar o nome do método extremamente estruturado:
    // Modelo: {ação}{limitação}{atributo}{comparação}{composição}{ordenação}
    //      ação       -> "find", "exists", "delete", ...
    //      limitação  -> <opcional> "top1", "top10", "top{n}"
    //      atributo   -> atributo existente na entidade
    //      comparação -> <opcional | default:Equals> "Equals", "LessThan", "GreaterThan", "Between", ...
    //      composição -> <opcional> "And", "Or"
    //      ordenação  -> <opcional> "OrderBy{atributo}Asc" "OrderBy{atributo}Desc"
    List<UsuarioEntity> findByNomeContainingIgnoreCaseAndLogin(String nome, String login);

    List<UsuarioEntity> findByNomeContainingIgnoreCase(String nome);

    List<UsuarioEntity> findByLogin(String login);

    Optional<UsuarioEntity> findTop1ByLogin(String login);

}
