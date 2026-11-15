#version 450 core

in float pass_normal;

out vec4 out_Colour;

uniform vec4 tintColour;

void main(){
    out_Colour = vec4(tintColour.r * -pass_normal, tintColour.g * -pass_normal, tintColour.b * -pass_normal, tintColour.a);
}