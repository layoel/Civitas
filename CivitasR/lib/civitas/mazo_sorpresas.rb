# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
module Civitas
  class MazoSorpresas
    
    def init
       @sorpresas = Array.new
       @cartasEspeciales = Array.new
       @barajada = false
       @usadas = 0
     end
     
    def initialize( debu=false )  
      @debug = debu
      @ultimaSorpresa = nil
      if (debu)
        init
        di = Diario.getInstance
        di.ocurreEvento("iniciado modo debug")
      end
    end
  
    def alMazo (s)
      unless @barajada
        @sorpresa << s
      end
    end
    
    def siguiente
      if @barajado == false or @usadas == @sorpresas.size
        @sorpresas.shuffle #barajo el mazo
        @usadas = 0
        @barajada = true
      end
      
      @usadas = @usadas+1
      @ultimaSorpresa = @sorpresas.at(0) #guardo la sorpresa en s
      @sorpresas.delete_at(0) #borro la sorpresa del inicio del mazo
      @sorpresas << @ultimaSorpresa #añado la sorpresa al final del mazo
    
      return @ultimaSorpresa
    end
  
    def inhabilitarCartaEspecial (sorpresa)
      unless ((s = @sorpresas.find(sorpresa)) == nil)
        @sorpresas.delete_at(s)
        @cartasEspeciales << sorpresa
        di =Diario.getinstance
        di.ocurre_evento("se ha inhabilitado una carta especial "+ sorpresa)
      end
    end
    
    def habilitarCartaEspecial (sorpresa)
      unless ((s = @cartasEspecuales.find(sorpresa)) == nil)
        @cartasEspeciales.delete_at(s)
        @sorpresas << sorpresa
        di =Diario.getinstance
        di.ocurre_evento("se ha habilitado una carta sorpresa "+ sorpresa)
      end
    end
    
    
  end
end